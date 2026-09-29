# FoodDelivery App

This repository contains an Android food delivery starter app and a backend API for Iraqi cities.

## Features
- Kotlin Android app
- Mobile login flow using Iraqi phone numbers
- Governorate selection
- Nearby restaurant list with distance sorting
- Secure encrypted local storage
- Language and theme settings
- Privacy policy and terms inside the app
- Backend API for restaurants and governorates
- Firebase-ready structure

## Android app
Open in Android Studio and sync Gradle. You can run the app in emulator or device.

## Backend
```bash
cd backend
npm install
cp .env.example .env
npm start
```

## Notes
- Add your real Firebase config and Google Maps key before production use.
- Replace mock login validation with Firebase Phone Auth.
- This version is a solid production-ready base for a food delivery app, but live backend and auth require actual project credentials.
