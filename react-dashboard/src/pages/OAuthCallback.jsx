import { useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";

export default function OAuthCallback() {
  const [searchParams] = useSearchParams();
  const nav = useNavigate();

  useEffect(() => {
    const token = searchParams.get("token"); // backend should redirect with ?token=<jwt>
    if (token) {
      localStorage.setItem("token", token);
      nav("/dashboard/user"); // redirect user after login
    } else {
      nav("/login"); // redirect to login if token missing
    }
  }, []);

  return <p>Loading...</p>;
}
