// src/pages/Home.js
import React from "react";
import "../styles/Home.css";
import { Link } from "react-router-dom";

const posts = [
  {
    id: 1,
    title: "Welcome to My Blog",
    author: "Nithin Kumar",
    content: "This is my blog Application...",
    date: "April 14, 2025",
  },
  {
    id: 2,
    title: "India is Awesome",
    author: "Rani",
    content: "India is largest country",
    date: "April 13, 2025",
  },
];

const Home = () => {
  return (
    <div className="home-container">
      <header className="home-header">
        <h1>📚 PlainWords</h1>
        <p>Explore thoughts. Share ideas.</p>
      </header>

      <div className="post-grid">
        {posts.map((post) => (
          <div key={post.id} className="post-card">
            <h3>{post.title}</h3>
            <p className="post-author">By {post.author}</p>
            <p>{post.content}</p>
            <div className="post-footer">
              <span>📅 {post.date}</span>
              <Link to={`/post/${post.id}`} className="read-more">Read More →</Link>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Home;
