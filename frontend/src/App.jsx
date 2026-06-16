import './App.css'

/**
 * Author: Rico Krenn
 * Created: 06/15/2026
 * Version: 1.0
 * Description: Routes
 * Project: 200_Abschlussprojekt
 */

import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';
import Layout from './components/Layout';
import Workouts from './pages/Workouts';
import Progressions from './pages/Progressions';
import Exercises from './pages/Exercises';
import Stats from './pages/Stats';

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
            <Route path="/workouts" element={<Workouts />} />
            <Route path="/progressions" element={<Progressions />} />
            <Route path="/exercises" element={<Exercises />} />
            <Route path="/stats" element={<Stats />} />
          </Route>
          <Route path="/login" element={<Login />} />
        </Routes>
      </BrowserRouter>
  )
}

export default App