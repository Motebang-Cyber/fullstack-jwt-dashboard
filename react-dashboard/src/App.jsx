// src/App.jsx
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Signup from "./pages/Signup";
import ForgotPassword from "./pages/ForgotPassword";
import ResetPassword from "./pages/ResetPassword";
import DashboardUser from "./pages/DashboardUser";
import DashboardAdmin from "./pages/DashboardAdmin";

import ProtectedRoute from "./components/ProtectedRoute";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Public routes */}
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/forgot" element={<ForgotPassword />} />
        <Route path="/reset/:token" element={<ResetPassword />} />

        {/* Protected routes with role-based access */}
        <Route
          path="/dashboard/user"
          element={
            <ProtectedRoute role="USER">
              <DashboardUser />
            </ProtectedRoute>
          }
        />

        <Route
          path="/dashboard/admin"
          element={
            <ProtectedRoute role="ADMIN">
              <DashboardAdmin />
            </ProtectedRoute>
          }
        />

        {/* Optional: Redirect unknown routes to login */}
        <Route path="*" element={<Login />} />
      </Routes>
    </BrowserRouter>
  );
}
