import React, { useState } from "react";
import axios from "axios";
import '../styles/CreatePost.css'; // ✅ Correct relative path

const CreatePost = () => {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [author, setAuthor] = useState("");
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!title || !content || !author) {
      setError("All fields are required.");
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:9100/blog/api/posts/createPost",
        {
          title,
          content,
          author,
        }
      );

      if (response.status === 200) {
        setSuccess("Post created successfully!");
        setTitle("");
        setContent("");
        setAuthor("");
        setError("");
      }
    } catch (err) {
      setError(err.response?.data?.message || "An error occurred while creating the post.");
    }
  };

  return (
    <div className="create-post-container">
      <h2>Create New Post</h2>
      <form onSubmit={handleSubmit}>
        {error && <p className="error">{error}</p>}
        {success && <p className="success">{success}</p>}
        <input
          type="text"
          placeholder="Enter post title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <textarea
          placeholder="Write your content..."
          value={content}
          onChange={(e) => setContent(e.target.value)}
        ></textarea>
        <input
          type="text"
          placeholder="Author"
          value={author}
          onChange={(e) => setAuthor(e.target.value)}
        />
        <button type="submit">Create Post</button>
      </form>
    </div>
  );
};

export default CreatePost;
