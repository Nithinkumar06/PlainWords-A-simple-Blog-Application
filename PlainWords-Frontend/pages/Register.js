import React, { useState } from "react";
import axios from "axios";
import "../styles/Auth.css";

const Register = () => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [profilePicture, setProfilePicture] = useState("");
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!username || !email || !password || !firstName || !lastName || !profilePicture) {
      setError("All fields are required.");
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:9100/blog/api/users/registerAsNewUser",
        {
          username,
          email,
          password,
          firstName,
          lastName,
          profilePicture,
        }
      );

      if (response.status === 200) {
        setSuccess("User registered successfully!");
        setUsername("");
        setEmail("");
        setPassword("");
        setFirstName("");
        setLastName("");
        setProfilePicture("");
        setError("");
      }
    } catch (err) {
      setError(err.response?.data?.message || "An error occurred during registration.");
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-card">
        <h2>Create Account ✨</h2>
        <p>Join the blog community</p>
        <form onSubmit={handleSubmit}>
          {error && <p className="error">{error}</p>}
          {success && <p className="success">{success}</p>}
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
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
          <input
            type="text"
            placeholder="First Name"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
            required
          />
          <input
            type="text"
            placeholder="Last Name"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
            required
          />
          <input
            type="url"
            placeholder="Profile Picture URL"
            value={profilePicture}
            onChange={(e) => setProfilePicture(e.target.value)}
            required
          />
          <button type="submit">Register</button>
        </form>
        <p className="switch-link">
          Already have an account? <a href="/login">Login</a>
        </p>
      </div>
    </div>
  );
};

export default Register;
