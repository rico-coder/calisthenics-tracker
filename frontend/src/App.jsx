import './App.css'

/**
 * Author: Rico Krenn
 * Created: 06/15/2026
 * Version: 2.0
 * Description: Routes
 * Project: 200_Abschlussprojekt
 */

import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';
import Layout from './components/Layout';
import LogWorkout from './pages/LogWorkout';
import Progressions from './pages/Progressions';
import History from './pages/History';
import Exercises from "./pages/Exercises.jsx";
import './custom.css'

function ProtectedRoute({ children }) {
  const token = localStorage.getItem('token')
  if (!token) {
    return <Navigate to="/login" />
  }
  return children
}

function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route element={<ProtectedRoute><Layout /></ProtectedRoute>}>
            <Route path="/" element={<Dashboard />} />
            <Route path="/workouts" element={<LogWorkout />} />
            <Route path="/progressions" element={<Progressions />} />
            <Route path="/history" element={<History />} />
            <Route path="/exercises" element={<Exercises />} />
          </Route>
          <Route path="/login" element={<Login />} />
        </Routes>
      </BrowserRouter>
  )
}

export default App