/**
 * Author: Rico Krenn
 * Created: 06/16/2026
 * Version: 1.0
 * Description: LogWorkout site (created with support of AI and understood code)
 * Project: 200_Abschlussprojekt
 */

"use client";

import { useState, useEffect } from 'react'
import { Container, Form, Button, FloatingLabel } from 'react-bootstrap'
import api from '../api/axios'

function LogWorkout() {
    const [date, setDate] = useState('')
    const [notes, setNotes] = useState('')
    const [effortRating, setEffortRating] = useState(7)
    const [exercises, setExercises] = useState([])
    const [exerciseBlocks, setExerciseBlocks] = useState([
        { exerciseId: '', sets: [{ reps: '' }] }
    ])

    useEffect(() => {
        api.get('/api/exercises')
            .then(response => setExercises(response.data))
            .catch(err => console.error(err))
    }, [])

    const addExercise = () => {
        setExerciseBlocks([...exerciseBlocks, { exerciseId: '', sets: [{ reps: '' }] }])
    }

    const removeExercise = (blockIndex) => {
        setExerciseBlocks(exerciseBlocks.filter((_, i) => i !== blockIndex))
    }

    const updateExerciseId = (blockIndex, value) => {
        const updated = [...exerciseBlocks]
        updated[blockIndex].exerciseId = value
        setExerciseBlocks(updated)
    }

    const addSet = (blockIndex) => {
        const updated = [...exerciseBlocks]
        updated[blockIndex].sets = [...updated[blockIndex].sets, { reps: '' }]
        setExerciseBlocks(updated)
    }

    const removeSet = (blockIndex, setIndex) => {
        const updated = [...exerciseBlocks]
        updated[blockIndex].sets = updated[blockIndex].sets.filter((_, i) => i !== setIndex)
        setExerciseBlocks(updated)
    }

    const updateReps = (blockIndex, setIndex, value) => {
        const updated = [...exerciseBlocks]
        updated[blockIndex].sets[setIndex].reps = value
        setExerciseBlocks(updated)
    }

    const handleSubmit = async (e) => {
        e.preventDefault()

        const body = {
            date: date,
            notes: notes,
            effortRating: effortRating,
            workoutExercises: exerciseBlocks.map(block => ({
                exercise: { id: parseInt(block.exerciseId) },
                workoutSets: block.sets.map((set, i) => ({
                    setNumber: i + 1,
                    reps: parseInt(set.reps)
                }))
            }))
        }

        try {
            await api.post('/api/workout', body)
            alert('Workout saved!')
        } catch (err) {
            console.error(err)
            alert('Something went wrong')
        }
    }

    return (
        <Container>
            <h1 className="fw-bold mt-4 text-body">Log Workout</h1>

            <Form className="mt-4" onSubmit={handleSubmit}>
                <Form.Group className="mb-3">
                    <Form.Label>Date</Form.Label>
                    <Form.Control
                        type="date"
                        value={date}
                        onChange={e => setDate(e.target.value)}
                    />
                </Form.Group>

                {exerciseBlocks.map((block, blockIndex) => (
                    <div key={blockIndex} className="border rounded p-3 mb-3">
                        <div className="d-flex justify-content-between align-items-center mb-2">
                            <strong>Exercise {blockIndex + 1}</strong>
                            {exerciseBlocks.length > 1 && (
                                <Button
                                    variant="outline-danger"
                                    size="sm"
                                    onClick={() => removeExercise(blockIndex)}
                                >
                                    Remove exercise
                                </Button>
                            )}
                        </div>

                        <Form.Select
                            value={block.exerciseId}
                            onChange={e => updateExerciseId(blockIndex, e.target.value)}
                            className="mb-2"
                        >
                            <option value="">Choose an exercise</option>
                            {exercises.map(exercise => (
                                <option key={exercise.id} value={exercise.id}>
                                    {exercise.name}
                                </option>
                            ))}
                        </Form.Select>

                        {block.sets.map((set, setIndex) => (
                            <div key={setIndex} className="d-flex gap-2 mb-2 align-items-center">
                                <Form.Control
                                    type="number"
                                    placeholder={`Set ${setIndex + 1} — reps`}
                                    value={set.reps}
                                    onChange={e => updateReps(blockIndex, setIndex, e.target.value)}
                                    min="1"
                                />
                                {block.sets.length > 1 && (
                                    <Button
                                        variant="outline-danger"
                                        size="sm"
                                        onClick={() => removeSet(blockIndex, setIndex)}
                                    >
                                        Remove
                                    </Button>
                                )}
                            </div>
                        ))}

                        <Button
                            variant="outline-primary"
                            size="sm"
                            onClick={() => addSet(blockIndex)}
                        >
                            + Add set
                        </Button>
                    </div>
                ))}

                <Button
                    variant="outline-primary"
                    className="w-100 mb-3"
                    onClick={addExercise}
                >
                    + Add exercise
                </Button>

                <Form.Group className="mb-3">
                    <Form.Label>Effort rating: {effortRating}/10</Form.Label>
                    <Form.Range
                        min="1"
                        max="10"
                        value={effortRating}
                        onChange={e => setEffortRating(Number(e.target.value))}
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