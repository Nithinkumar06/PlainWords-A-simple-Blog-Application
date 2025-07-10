import React, { useState } from "react";
import { useParams } from "react-router-dom";
import "../styles/PostDetail.css"; // 👈 Import CSS

const PostDetail = () => {
  const { id } = useParams();
  const [likes, setLikes] = useState(0);
  const [liked, setLiked] = useState(false);
  const [comments, setComments] = useState([]);
  const [commentText, setCommentText] = useState("");

  const toggleLike = () => {
    setLiked(!liked);
    setLikes((prev) => (liked ? prev - 1 : prev + 1));
  };

  const addComment = (e) => {
    e.preventDefault();
    if (commentText.trim()) {
      setComments([...comments, commentText]);
      setCommentText("");
    }
  };

  return (
    <div className="post-detail-container">
      <div className="post-content">
        <h2>Post #{id}</h2>
        <p>This is the post content. You can replace this with content from backend.</p>
        <button className="like-button" onClick={toggleLike}>
          {liked ? "❤️ Liked" : "🤍 Like"} ({likes})
        </button>
      </div>

      <div className="comments-section">
        <h3>Comments</h3>
        <form className="comment-form" onSubmit={addComment}>
          <input
            type="text"
            placeholder="Write a comment..."
            value={commentText}
            onChange={(e) => setCommentText(e.target.value)}
          />
          <button type="submit">Post</button>
        </form>

        <ul className="comment-list">
          {comments.map((comment, index) => (
            <li key={index}>💬 {comment}</li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default PostDetail;
