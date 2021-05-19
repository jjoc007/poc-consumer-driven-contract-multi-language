package controller

import (
	"encoding/json"
	"github.com/jjoc007/poc-consumer-driven-contract-multi-language/payments/model"
	"github.com/jjoc007/poc-consumer-driven-contract-multi-language/payments/service"
	"net/http"
	"strings"
)

var PaymentService = &service.PaymentService{
	Payments: map[string]*model.Payment{
		"pencil": {
			ID:       "pencil",
			Name:     "pencil-123",
			Amount:   "10000",
			Category: "office",
				},
	},
}

// GetPayment fetches a payment
func GetPayment(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	// Get username from path
	a := strings.Split(r.URL.Path, "/")
	id := a[len(a)-1]

	user, err := PaymentService.ByID(id)
	if err != nil {
		w.WriteHeader(http.StatusNotFound)
	} else {
		w.WriteHeader(http.StatusOK)
		resBody, _ := json.Marshal(user)
		w.Write(resBody)
	}
}

func GetHTTPHandler() *http.ServeMux {
	mux := http.NewServeMux()
	mux.HandleFunc("/payment/", GetPayment)

	return mux
}