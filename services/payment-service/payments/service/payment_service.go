package service

import "github.com/jjoc007/poc-consumer-driven-contract-multi-language/payments/model"

// PaymentService is an in-memory db representation of our set of payments
type PaymentService struct {
	Payments map[string]*model.Payment
}

// ByID finds a payment by their ID
func (u *PaymentService) ByID(ID string) (*model.Payment, error) {
	for _, p := range u.Payments {
		if p.ID == ID {
			return p, nil
		}
	}
	return nil, model.ErrNotFound
}