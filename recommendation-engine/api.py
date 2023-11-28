from flask import Flask, jsonify, request
import pandas as pd
# Import Numpy
import numpy as np
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

# Create the Flask application
app = Flask(__name__)
# DataFrame containing coffee attributes
coffee_data = pd.read_csv('coffee.csv')

# Create a column 'combined_features' that combines text attributes
coffee_data['combined_features'] = coffee_data['Coffee_Type'] + ' ' + coffee_data['Coffee_Shop'] + ' ' + coffee_data['Flavour'] + ' ' + coffee_data['Milk_Type'] + ' ' + coffee_data['Temperature'] + ' ' + coffee_data['Roast_Type'] + ' ' + coffee_data['Origin']

@app.route('/', methods=['GET'])
# Function to recommend coffee based on TF-IDF and cosine similarity
def recommend_coffee():
    max_recommendations = 5

    #Get the selected attributes and values from URL
    attributes = [(k, v) for k, v in request.args.items()]
    # Filter the dataset based on the selected attributes
    filtered_dataset = coffee_data.copy()
    for attribute, value in attributes:
        if pd.notnull(value) and value.strip() != '':
            if attribute == 'Origin':
                # Handle flavor attribute with multiple values
                filtered_dataset = filtered_dataset[filtered_dataset[attribute].apply(lambda x: value in x.split(', '))]
            else:
                filtered_dataset = filtered_dataset[filtered_dataset[attribute] == value]
                
    # Fill missing values with an empty string before TF-IDF vectorization
    filtered_dataset.fillna('', inplace=True)
    
    # Perform TF-IDF vectorization on the 'combined_features' column
    vectorizer = TfidfVectorizer()
    tfidf_matrix = vectorizer.fit_transform(filtered_dataset['combined_features'])
    
    # Calculate cosine similarity between the selected attributes and all coffees
    input_tfidf = vectorizer.transform([' '.join([value for _, value in attributes if pd.notnull(value) and value.strip() != ''])])
    cosine_sim = cosine_similarity(tfidf_matrix, input_tfidf)
    
    # Get indices of top N similar coffees
    top_indices = cosine_sim.flatten().argsort()[::-1][:max_recommendations]
    
    # Get the top N recommended coffee names
    top_coffee_names = filtered_dataset['Coffee_Name'].iloc[top_indices].tolist()
    
    selected_df = filtered_dataset[filtered_dataset['Coffee_Name'].isin(top_coffee_names)]

    return selected_df.to_dict(orient='records')



if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=True)