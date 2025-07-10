import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // Import useNavigate for navigation
import "../styles/Auth.css";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate(); // Initialize useNavigate

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Frontend validation
    if (!email || !password) {
      setError("Both email and password are required.");
      return;
    }

    try {
      console.log("Fetching user data from API...");

      // Fetch user data from the API
      const response = await fetch("http://localhost:9100/api/auth/users", {
        method: "GET",
        headers: { "Content-Type": "application/json" },
      });

      if (response.ok) {
        const users = await response.json(); // Assume the API returns a list of users
        console.log("Fetched users:", users);

        // Compare entered data with fetched data
        const user = users.find(
          (u) => u.email === email && u.password === password
        );

        if (user) {
          console.log("Login successful:", user);

          // Navigate to the dashboard or another page
          navigate("/dashboard");
        } else {
          setError("Invalid email or password.");
        }
      } else {
        console.error("Failed to fetch users from API.");
        setError("An error occurred while fetching user data.");
      }
    } catch (err) {
      console.error("Login error:", err);
      setError("An error occurred. Please try again later.");
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-card">
        <h2>Welcome Back 👋</h2>
        <p>Login to your account</p>
        <form onSubmit={handleSubmit}>
          {error && <p className="error">{error}</p>} {/* Display error message */}
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
          <button type="submit">Login</button>
        </form>
        <p className="switch-link">
          Don't have an account? <a href="/register">Register</a>
        </p>
      </div>
    </div>
  );
};

export default Login;