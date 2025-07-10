import React, { useState } from "react";
import "../styles/Create.css";

const Create = () => {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    alert(`Post Created!\nTitle: ${title}\nContent: ${content}`);
    // Add submission logic here
  };

  return (
    <div className="create-page">
      <div className="create-card">
        <h2>Create a New Post 📝</h2>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Post Title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
          />
          <textarea
            placeholder="Write your content here..."
            value={content}
            onChange={(e) => setContent(e.target.value)}
            rows="6"
            required
          />
          <button type="submit">Publish Post</button>
        </form>
      </div>
    </div>
  );
};

export default Create;
