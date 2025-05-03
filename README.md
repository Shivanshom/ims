# 🗃️ Inventory Management System (IMS)

This monorepo contains:

- 🧠 **Backend** – Spring Boot (`ims/`)
- 💻 **Frontend** – JavaScript Web App (`inms-frontend/`)
- 📱 **Mobile App** – React Native with Expo (`ims-native/`)

---

## 🧠 Backend – `ims/`

```bash
cd ims

### Backend
```bash
cd ims
Open the project in IntelliJ IDEA

Run: mvn clean install

Start the backend from IntelliJ (Spring Boot runner)

The backend runs on http://localhost:8080. Use this as the base URL for both frontend and mobile (with adjustments if needed).

### Frontend
cd ims-frontend
npm install
npm start
By default, the frontend assumes the backend is running at http://localhost:8080.


### Mobile
cd ims-native
npm install
npx expo start

Important: Mobile devices cannot access localhost. Use your local IP address instead:

You can find your IP by running ipconfig (Windows) or ifconfig (Mac/Linux).
