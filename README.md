# Android Food Delivery App

This project is a complete starter for a food delivery Android app with:

- Kotlin Android app
- secure encrypted local storage
- phone login flow
- Iraqi governorates selection
- nearby restaurant list
- settings section with language and theme switching
- privacy policy and terms inside the app
- backend API folder for Node.js/Express
- sample restaurant and governorate data
- Firebase-ready integration setup

## Project structure

- `app/` Android project
- `backend/` Node.js API and mocked JSON data

## Android Studio

Open the repo in Android Studio and let Gradle sync. The project is structured so you can extend it with Firebase Authentication, Firestore, and Google Maps.

## Backend

```bash
cd backend
npm install
cp .env.example .env
npm start
```

## Notes

- Add your real Firebase config and service account to connect to live backend data.
- Replace mock phone validation with Firebase Phone Auth for production.
- Add real Google Maps API key in the Android manifest / Maps configuration for live geolocation.
