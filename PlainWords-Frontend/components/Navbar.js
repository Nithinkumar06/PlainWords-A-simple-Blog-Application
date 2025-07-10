// src/components/Navbar.js
import React from "react";
import { Link } from "react-router-dom";
import "../styles/Navbar.css";

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-left">
        {/* Changed logo from 📘 to 📝 */}
        <Link to="/" className="logo">📝 PlainWords</Link>
      </div>
      <div className="navbar-right">
        <Link to="/" className="nav-link">Home</Link>
        <Link to="/create" className="nav-link">Create</Link>
        <Link to="/login" className="nav-button">Login</Link>
        <Link to="/register" className="nav-button register">Register</Link>
      </div>
    </nav>
  );
};

export default Navbar;
