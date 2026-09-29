require('dotenv').config();
const express = require('express');
const cors = require('cors');
const admin = require('firebase-admin');
const fs = require('fs');
const path = require('path');

const app = express();
const PORT = process.env.PORT || 3000;

app.use(cors());
app.use(express.json());

// Example: initialize Firebase Admin SDK if service account is configured.
if (process.env.FIREBASE_PROJECT_ID) {
  const serviceAccount = process.env.FIREBASE_SERVICE_ACCOUNT_PATH
    ? require(path.resolve(process.cwd(), process.env.FIREBASE_SERVICE_ACCOUNT_PATH))
    : null;

  if (serviceAccount) {
    admin.initializeApp({
      credential: admin.credential.cert(serviceAccount),
      databaseURL: `https://${process.env.FIREBASE_PROJECT_ID}.firebaseio.com`
    });
  }
}

const governorates = JSON.parse(
  fs.readFileSync(path.join(__dirname, 'data', 'governorates.json'), 'utf8')
);

const restaurants = JSON.parse(
  fs.readFileSync(path.join(__dirname, 'data', 'restaurants.json'), 'utf8')
);

app.get('/api/health', (req, res) => {
  res.json({ status: 'ok', service: 'food-delivery-backend' });
});

app.get('/api/governorates', (req, res) => {
  res.json(governorates);
});

app.get('/api/restaurants', (req, res) => {
  const { governorate } = req.query;
  if (governorate) {
    const filtered = restaurants.filter(r => r.governorate.toLowerCase() === governorate.toLowerCase());
    return res.json(filtered);
  }
  return res.json(restaurants);
});

app.get('/api/restaurants/:id', (req, res) => {
  const restaurant = restaurants.find(r => String(r.id) === String(req.params.id));
  if (!restaurant) return res.status(404).json({ message: 'Restaurant not found' });
  return res.json(restaurant);
});

app.post('/api/orders', (req, res) => {
  const { userPhone, restaurantId, items } = req.body;
  if (!userPhone || !restaurantId || !items) {
    return res.status(400).json({ message: 'Missing order fields' });
  }
  return res.status(201).json({
    message: 'Order accepted',
    order: {
      userPhone,
      restaurantId,
      items,
      status: 'pending'
    }
  });
});

app.listen(PORT, () => {
  console.log(`Food delivery backend running on http://localhost:${PORT}`);
});
