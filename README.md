# find-your-desired-coffee
[2308-CS230] 2020819696 (06) MUHAMMAD HAZIQ BIN AHMAD RAJI
‘Find Your Desired Coffee’: A Coffee Recommendation System Using Content-Based Filtering Technique

Welcome to Find Your Desired Coffee! This app is designed to help users discover and select delicious coffee drinks based on their preferences. This app is a part of my final year project as a Bachelor of Computer Science student in Universiti Teknologi MARA (UiTM) Malacca Campus Jasin Branch.

## Table of Contents
- [Features]
- [Setup]
  - [Recommendation Engine]
  - [Android App]
- [Usage]

## Features
- Receive personalized coffee drink recommendations based on user preferences.
- View details about each recommended coffee drink
- User-friendly interface for a seamless experience.

## Setup

### Recommendation Engine
1. Clone this repository.
2. Navigate to the `recommendation-engine` directory: `cd recommendation-engine`.
3. Install the required Python packages: `pip install -r requirements.txt`.
4. Place your dataset CSV file containing coffee drink information in the same directory as `api.py`.
5. Run the Flask server: `python api.py`.
6. Note the server address (e.g., `http://127.0.0.1:5000`).

### Android App
1. Open Android Studio.
2. Clone this repository if you haven't already.
3. Open the `android-app\CoffeeRecommender` directory.
4. Update `ApiUtils.java` with the Flask server address.
5. Build and run the app on an emulator or physical device.

## Usage
1. Launch the app on your Android device.
2. Set your coffee preferences.
3. Click the "RECOMMEND A COFFEE" button.
4. View the recommended coffee drinks and their details.
