import { useState } from "react";
import { login } from "../api/auth";
import { useNavigate, Link } from "react-router-dom";
import GithubLoginButton from "../components/GithubLoginButton";

export default function Login() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);

  async function submit(e) {
    e.preventDefault();
    setLoading(true);
    try {
      const res = await login(email, password);
      // Save JWT token to localStorage
      localStorage.setItem("token", res.data.token);

      // Optional: store role
      if (res.data.role) localStorage.setItem("role", res.data.role);

      // Navigate based on role
      if (res.data.role === "ADMIN") navigate("/dashboard/admin");
      else navigate("/dashboard/user");
    } catch (err) {
      alert("Invalid login credentials!");
      console.error(err);
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="container">
      <h2>Login</h2>
      <form onSubmit={submit}>
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <button type="submit" disabled={loading}>
          {loading ? "Logging in..." : "Login"}
        </button>
      </form>

      <GithubLoginButton />

      <p>
        No account? <Link to="/signup">Signup</Link>
      </p>
      <p>
        Forgot password? <Link to="/forgot">Reset</Link>
      </p>
    </div>
  );
}
