import { Navigate } from "react-router-dom";

/**
 * ProtectedRoute checks for:
 * 1. JWT token in localStorage
 * 2. Optional role matching
 *
 * Props:
 * - role (string, optional): "USER" or "ADMIN"
 * - children: the component to render if authorized
 */
export default function ProtectedRoute({ role, children }) {
  const token = localStorage.getItem("token");
  const userRole = localStorage.getItem("role"); // role stored after login

  if (!token) {
    // No token, redirect to login
    return <Navigate to="/login" replace />;
  }

  if (role && role !== userRole) {
    // Role mismatch, redirect to login
    return <Navigate to="/login" replace />;
  }

  // Authorized
  return children;
}
