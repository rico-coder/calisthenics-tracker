/**
 * Author: Rico Krenn
 * Created: 06/16/2026
 * Version: 2.0
 * Description: Dashboard site
 * Project: 200_Abschlussprojekt
 */

import { useState, useEffect } from 'react'
import { Container, Card } from 'react-bootstrap'
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Tooltip, Legend } from 'chart.js'
import { Bar } from 'react-chartjs-2'
import api from '../api/axios'

ChartJS.register(CategoryScale, LinearScale, BarElement, Tooltip, Legend)

function Dashboard() {
    const [volumeData, setVolumeData] = useState([])
    const [recentWorkouts, setRecentWorkouts] = useState([])

    // Access to Endpoints
    useEffect(() => {
        api.get('/api/stats/volume')
            .then(response => setVolumeData(response.data))

        api.get('/api/workout')
            .then(response => {
                const sorted = [...response.data]
                    .sort((a, b) => new Date(b.date) - new Date(a.date))
                    .slice(0, 5)
                setRecentWorkouts(sorted)
            })
    }, [])

    // Max 8 weeks
    const recentVolume = volumeData.slice(-8)

    // Chart.js configuration
    const chartData = {
        labels: recentVolume.map(v => v.week),
        datasets: [{
            label: 'Total sets',
            data: recentVolume.map(v => v.totalSets),
            backgroundColor: '#3cb371'
        }]
    }

    return (
        <Container>
            <h1 className="fw-bold mt-4 text-body">Dashboard</h1>

            <Card className="mt-4 p-3">
                <h5>Weekly volume</h5>
                <Bar data={chartData} />
            </Card>

            <h5 className="mt-4">Recent workouts</h5>

            { recentWorkouts.map(workout => (
                <Card key={workout.id} className="mb-3 mt-3">
                    <Card.Body>
                        <Card.Title className="d-flex justify-content-between">
                            <span>{workout.date}</span>
                            <span className="text-muted fs-6">
                                Effort: {workout.effortRating}/10
                            </span>
                        </Card.Title>
                        {(workout.workoutExercises || []).map(we => (
                            <div key={we.id}>
                                {we.exercise.name}: {we.workoutSets.length} sets
                            </div>
                        ))}
                    </Card.Body>
                </Card>
            ))}

        </Container>
    )
}

export default Dashboard
