package model

import "errors"

var ErrNotFound = errors.New("not found")

type Payment struct {
	ID     string `json:"id" pact:"example=123456"`
	Name   string `json:"name" pact:"example=test payment"`
	Amount string `json:"amount" pact:"example=123456"`
	Category   string `json:"category" pact:"example=it,regex=^(it|home|office)$"`
}