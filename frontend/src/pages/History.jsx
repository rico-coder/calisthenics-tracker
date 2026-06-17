/**
 * Author: Rico Krenn
 * Created: 06/16/2026
 * Version: 1.0
 * Description: History site
 * Project: 200_Abschlussprojekt
 */

"use client";
import { Card, Container } from 'react-bootstrap'
import { useEffect, useState } from 'react'
import api from '../api/axios.js'

function History() {
    const [sessions, setSessions] = useState([])

    useEffect(() => {
        api.get('/api/workout')
            .then(response => {
                const sorted = [...response.data].sort((a, b) =>
                    new Date(b.date) - new Date(a.date)
                )
                setSessions(sorted)
            })
            .catch(err => console.log(err))
    }, [])

    return (
        <Container>
            <h1 className="fw-bold mt-4">History</h1>

            {sessions.length === 0 && (
                <p className="text-muted mt-3">No workouts logged yet.</p>
            )}

            {sessions.map(session => (
                <Card key={session.id} className="mb-3 mt-3">
                    <Card.Body>
                        <Card.Title className="d-flex justify-content-between">
                            <span>{session.date}</span>
                            <span className="text-muted fs-6">
                                Effort: {session.effortRating}/10
                            </span>
                        </Card.Title>
                        {(session.workoutExercises || []).map(we => (
                            <div key={we.id}>
                                {we.exercise.name}: {we.workoutSets.length} sets
                            </div>
                        ))}
                        {session.notes && (
                            <Card.Text className="mt-2 text-muted">
                                {session.notes}
                            </Card.Text>
                        )}
                    </Card.Body>
                </Card>
            ))}
        </Container>
    )
}

export default History