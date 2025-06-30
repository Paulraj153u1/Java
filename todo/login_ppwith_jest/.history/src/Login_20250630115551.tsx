import React, { useState } from 'react';

type LoginForm = {
  email: string;
  password: string;
};

const Login: React.FC = () => {
  const [formData, setFormData] = useState<LoginForm>({
    email: '',
    password: '',
  });

  const [error, setError] = useState<string>('');

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();


     console.log('Logging in with:', formData);
    // Basic validation
    if (!formData.email || !formData.password) {
        setError('Email and Password are required');
        return;
        }
    // Simulate a successful login for specific credentials
    // You can replace this with actual authentication logic


    // For example, you can check against a hardcoded user or call an API
    // Here, we are simulating a successful login for a specific email and password

    if (formData.email ==="paulraj@gmail.com" && formData.password === "password123") {
      alert("Login Successful!");
    //   return;
        
    }
    else {
    //   setError('Invalid email or password');
      alert("Invalid email or password");
    //   return;
    }

    // if (!formData.email || !formData.password) {
    //   setError('Email and Password are required');
    //   return;
    // }

    // Simulate login logic
   
    setError('');
    // alert('Login Successful!');
  };

  return (
    <div style={styles.container}>
      <h2>Login</h2>
      <form onSubmit={handleSubmit} style={styles.form}>
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
          style={styles.input}
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={formData.password}
          onChange={handleChange}
          style={styles.input}
        />
        {error && <p style={styles.error}>{error}</p>}
        <button type="submit" style={styles.button}>Login</button>
      </form>
    </div>
  );
};

const styles: { [key: string]: React.CSSProperties } = {
  container: {
    width: '300px',
    margin: '50px auto',
    padding: '20px',
    border: '1px solid #ccc',
    borderRadius: '10px',
  },
  form: {
    display: 'flex',
    flexDirection: 'column',
    gap: '12px',
  },
  input: {
    padding: '10px',
    fontSize: '16px',
  },
  button: {
    padding: '10px',
    fontSize: '16px',
    cursor: 'pointer',
  },
  error: {
    color: 'red',
  }
};

export default Login;
