import { useState } from "react";
import { forgotPassword } from "../api/auth";

export default function ForgotPassword() {
  const [email, setEmail] = useState("");

  async function submit(e) {
    e.preventDefault();
    await forgotPassword(email);
    alert("Reset email sent!");
  }

  return (
    <div className="container">
      <h2>Forgot Password</h2>

      <form onSubmit={submit}>
        <input placeholder="Email"
          onChange={(e) => setEmail(e.target.value)} />
        <button>Send Reset Link</button>
      </form>
    </div>
  );
}
