import React from 'react';
import './ProfilePage.css';

const dummyPosts = [
  { title: 'My First Blog', date: 'April 10, 2025' },
  { title: 'Exploring React Hooks', date: 'April 13, 2025' },
];

const ProfilePage = () => {
  return (
    <div className="profile-container">
      <div className="profile-card">
        <img src="/default-profile.png" alt="User Avatar" />
        <h2>John Doe</h2>
        <p>Email: john@example.com</p>
        <p>Member since: Jan 2025</p>
      </div>

      <div className="user-posts">
        <h3>My Posts</h3>
        {dummyPosts.map((post, index) => (
          <div className="post-item" key={index}>
            <h4>{post.title}</h4>
            <span>{post.date}</span>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProfilePage;
