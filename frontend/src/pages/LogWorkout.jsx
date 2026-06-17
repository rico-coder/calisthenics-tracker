/**
 * Author: Rico Krenn
 * Created: 06/16/2026
 * Version: 1.0
 * Description: LogWorkout site (handleSubmit and useEffect created with support of AI)
 * Project: 200_Abschlussprojekt
 */

"use client";

import { useState, useEffect } from 'react'
import { Container, Form, Button, FloatingLabel } from 'react-bootstrap'
import api from '../api/axios'

function LogWorkout() {
    const [exerciseId, setExerciseId] = useState('')
    const [date, setDate] = useState('')
    const [notes, setNotes] = useState('')
    const [effortRating, setEffortRating] = useState(7)
    const [sets, setSets] = useState([{ reps: '' }])
    const [exercises, setExercises] = useState([])

    const addSet = () => {
        setSets([...sets, { reps: '' }])
    }

    const removeSet = (index) => {
        setSets(sets.filter((item, i) => i !== index))
    }

    const updateReps = (index, value) => {
        const updated = [...sets]
        updated[index].reps = value
        setSets(updated)
    }

    useEffect(() => {
        api.get('/api/exercises')
            .then(response => setExercises(response.data))
            .catch(err => console.error(err));
    }, [])

    const handleSubmit = async (e) => {
        e.preventDefault()

        const body = {
            date: date,
            notes: notes,
            effortRating: effortRating,
            workoutExercises: [
                {
                    exercise: { id: Number.parseInt(exerciseId) },
                    workoutSets: sets.map((set, index) => ({
                        setNumber: index + 1,
                        reps: Number.parseInt(set.reps)
                    }))
                }
            ]
        }

        try {
            await api.post('/api/workout', body)
            // success — clear form or navigate away
            alert('Workout saved!')
        } catch (err) {
            console.error(err)
            alert('Something went wrong')
        }
    }

    return (
        <Container>
            <h1 className="fw-bold mt-4">Log Workout</h1>

            <Form className="mt-4" onSubmit={handleSubmit}>
                <Form.Group className="mb-3">
                    <Form.Label>Exercise</Form.Label>
                    <Form.Select
                        value={exerciseId}
                        onChange={e => setExerciseId(e.target.value)}
                    >
                        <option value="">Choose an exercise</option>
                        {exercises.map(exercise => (
                            <option key={exercise.id} value={exercise.id}>
                                {exercise.name}
                            </option>
                        ))}
                    </Form.Select>
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Date</Form.Label>
                    <Form.Control
                        type="date"
                        value={date}
                        onChange={e => setDate(e.target.value)}
                    />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Sets</Form.Label>
                    {sets.map((set, index) => (
                            <div key={index} className="d-flex gap-2 mb-2 align-items-center">
                            <Form.Control
                                type="number"
                                placeholder={`Set ${index + 1} - reps`}
                                value={set.reps}
                                onChange={e => updateReps(index, e.target.value)}
                                min="1"
                            />
                            {sets.length > 1 && (
                                <Button
                                    variant="outline-danger"
                                    size="sm"
                                    onClick={() => removeSet(index)}
                                >
                                    Remove
                                </Button>
                            )}
                        </div>
                    ))}
                    <Button variant="outline-primary" size="sm" onClick={addSet}>
                        + Add set
                    </Button>
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Effort rating: {effortRating}/10</Form.Label>
                    <Form.Range
                        min="1"
                        max="10"
                        value={effortRating}
                        onChange={e  => setEffortRating(Number(e.target.value))}
                    />
                </Form.Group>

                <FloatingLabel label="Notes" className="mb-3">
                    <Form.Control
                        as="textarea"
                        placeholder="Notes"
                        style={{ height: '100px' }}
                        value={notes}
                        onChange={e => setNotes(e.target.value)}
                    />
                </FloatingLabel>

                <Button variant="primary" type="submit" className="w-100">
                    Save Workout
                </Button>
            </Form>
        </Container>
    )
}

export default LogWorkout