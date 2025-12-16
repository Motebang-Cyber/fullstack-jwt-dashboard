import { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { resetPassword } from "../api/auth";

export default function ResetPassword() {
  const nav = useNavigate();
  const { token } = useParams();
  const [newPassword, setNewPassword] = useState("");

  async function submit(e) {
    e.preventDefault();
    await resetPassword(token, newPassword);
    alert("Password changed!");
    nav("/login");
  }

  return (
    <div className="container">
      <h2>Reset Password</h2>
      <form onSubmit={submit}>
        <input
          type="password"
          placeholder="New Password"
          onChange={(e) => setNewPassword(e.target.value)}
        />
        <button>Update Password</button>
      </form>
    </div>
  );
}