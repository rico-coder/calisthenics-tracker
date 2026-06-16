/**
 * Author: Rico Krenn
 * Created: 06/16/2026
 * Version: 1.0
 * Description: Layout, that is on every site the same.
 * Project: 200_Abschlussprojekt
 */
import { Outlet, NavLink, useNavigate } from 'react-router-dom'
import { Navbar, Nav, Container, Button } from 'react-bootstrap'
import { Sun, Moon } from 'lucide-react'
import { useEffect, useState } from 'react'

function Layout() {
    const navigate = useNavigate()
    const [theme, setTheme] = useState('dark')

    const handleLogout = () => {
        localStorage.removeItem('token')
        navigate('/login')
    }

    const toggleTheme = () => {
        const newTheme = theme === 'dark' ? 'light' : 'dark'
        setTheme(newTheme)
        document.documentElement.dataset.bsTheme = newTheme
    }

    useEffect(() => {
        document.documentElement.dataset.bsTheme = theme
    }, [])

    return (
        <>
            <Navbar expand="lg" className="bg-body-tertiary px-3">
                <Container fluid>
                    <Navbar.Brand as={NavLink} to="/">
                        Calisthenics Tracker
                    </Navbar.Brand>

                    <div className="d-flex gap-2 ms-auto me-2">
                        <Button variant="primary" size="sm" onClick={toggleTheme}>
                            {theme === 'dark' ? <Sun size={20} /> : <Moon size={20} />}
                        </Button>
                        <Button variant="outline-danger" size="sm" onClick={handleLogout}>
                            Logout
                        </Button>
                    </div>

                    <Navbar.Toggle aria-controls="main-nav" />
                    <Navbar.Collapse id="main-nav">
                        <Nav className="me-auto">
                            <Nav.Link as={NavLink} to="/">Dashboard</Nav.Link>
                            <Nav.Link as={NavLink} to="/workouts">Log Workout</Nav.Link>
                            <Nav.Link as={NavLink} to="/progressions">Progressions</Nav.Link>
                            <Nav.Link as={NavLink} to="/exercises">Exercises</Nav.Link>
                            <Nav.Link as={NavLink} to="/stats">Stats</Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>

            <Container className="mt-4 px-3">
                <Outlet />
            </Container>
        </>
    )
}

export default Layout