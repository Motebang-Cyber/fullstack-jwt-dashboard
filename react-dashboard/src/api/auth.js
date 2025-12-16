import api from "./axios";

export const login = (email, password) =>
  api.post("/auth/login", { email, password });

export const signup = (email, password) =>
  api.post("/auth/signup", { email, password });

export const forgotPassword = (email) =>
  api.post("/auth/forgot", { email });

export const resetPassword = (token, newPassword) =>
  api.post("/auth/reset", { token, newPassword });
