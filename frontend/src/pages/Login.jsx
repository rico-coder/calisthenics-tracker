/**
 * Author: Rico Krenn
 * Created: 06/15/2026
 * Version: 2.0
 * Description: Frontend login page
 * Project: 200_Abschlussprojekt
 */

"use client";

import {useState, useEffect} from "react";
import {Button, Container, Form, Alert} from "react-bootstrap";
import {Moon, Sun} from 'lucide-react';
import {useNavigate} from "react-router-dom";
import api from "../api/axios.js";

function Login() {
    // State for username
    const [username, setUsername] = useState('');
    // State for password
    const [password, setPassword] = useState('');
    //
    const navigate = useNavigate()
    const [error, setError] = useState('')

    // validates the inputs
    const [validated, setValidated] = useState(false);

    const handleSubmit = async (event) => {
        event.preventDefault()
        const form = event.currentTarget;

        if (form.checkValidity() === false) {
            event.stopPropagation()
            setValidated(true)
            return
        }
        setValidated(true);

        try {
            const response = await api.post('/api/auth/login', { username, password })
            localStorage.setItem('token', response.data.jwt)
            navigate('/')
        } catch (err) {
            setError('Invalid username or password')
        }
    };

// 1. Read localStorage as the initial state
    const [theme, setTheme] = useState(
        localStorage.getItem('theme') || 'light'
    )

// 2. Toggle saves to localStorage
    const toggleTheme = () => {
        const newTheme = theme === 'dark' ? 'light' : 'dark'
        setTheme(newTheme)
        document.documentElement.dataset.bsTheme = newTheme
        localStorage.setItem('theme', newTheme)
    }

// 3. Apply on mount from state (which already read localStorage)
    useEffect(() => {
        document.documentElement.dataset.bsTheme = theme
    }, [theme])

    return (
        <Container>
            <Button variant="primary" type="button" className="d-flex justify-content-end mt-4 m-2" onClick={toggleTheme}>
                    {theme === 'dark' ? <Sun /> : <Moon />}
            </Button>
            <h1 className="fw-bold mt-1 text-body">Login</h1>
            {error && <Alert variant="danger">{error}</Alert>}
            <Form className="mt-5" noValidate validated={validated} onSubmit={handleSubmit}>
                <Form.Group className="my-4 mx-5" controlId="formBasicEmail">
                    <Form.Label>Username</Form.Label>
                    <Form.Control
                        type="text"
                        value={username}
                        onChange={event => setUsername(event.target.value)}
                        placeholder="Enter username"
                        required
                    />
                </Form.Group>
                <Form.Group className="my-4 mx-5" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                        type="password"
                        value={password}
                        onChange={event => setPassword(event.target.value)}
                        placeholder="Enter password"
                        required/>
                </Form.Group>
                <Button variant="primary" type="submit" className="mb-1">
                    Login
                </Button>
            </Form>
        </Container>
    );
}

export default Login;
