package controller_test

import (
	"fmt"
	"github.com/jjoc007/poc-consumer-driven-contract-multi-language/payments/controller"
	"github.com/jjoc007/poc-consumer-driven-contract-multi-language/payments/model"
	"github.com/jjoc007/poc-consumer-driven-contract-multi-language/payments/service"
	"github.com/pact-foundation/pact-go/dsl"
	"github.com/pact-foundation/pact-go/types"
	"github.com/pact-foundation/pact-go/utils"
	"log"
	"net"
	"net/http"
	"os"
	"testing"
)

// Configuration / Test Data
var dir, _ = os.Getwd()
var logDir = fmt.Sprintf("%s/log", dir)
var port, _ = utils.GetFreePort()

// The Provider verification
func TestPactProvider(t *testing.T) {
	go startInstrumentedProvider()

	pact := createPact()

	// Verify the Provider - Tag-based Published Pacts for any known consumers
	_, err := pact.VerifyProvider(t, types.VerifyRequest{
		ProviderBaseURL: fmt.Sprintf("http://127.0.0.1:%d", port),
		Tags:            []string{"test"},
		BrokerURL:                  "http://localhost:8000",
		BrokerUsername:             "pact_workshop",
		BrokerPassword:             "pact_workshop",
		PublishVerificationResults: true,
		ProviderVersion:            "1.0.0",
		StateHandlers:              stateHandlers,
		BeforeEach: func() error {
			controller.PaymentService = paymentExist
			return nil
		},
	})

	if err != nil {
		t.Log(err)
	}
}

var stateHandlers = types.StateHandlers{
	"payment exist": func() error {
		controller.PaymentService = paymentExist
		return nil
	},
}

var paymentExist = &service.PaymentService{
	Payments: map[string]*model.Payment{
		"pencil": {
			ID:       "pencil",
			Name:     "pencil-123",
			Amount:   "10000",
			Category: "office",
		},
	},
}


// Starts the provider API with hooks for provider states.
// This essentially mirrors the main.go file, with extra routes added.
func startInstrumentedProvider() {
	mux := controller.GetHTTPHandler()

	ln, err := net.Listen("tcp", fmt.Sprintf(":%d", port))
	if err != nil {
		log.Fatal(err)
	}
	defer ln.Close()

	log.Printf("API starting: port %d (%s)", port, ln.Addr())
	log.Printf("API terminating: %v", http.Serve(ln, mux))

}

// Setup the Pact client.
func createPact() dsl.Pact {
	return dsl.Pact{
		Provider: "payment-service",
		LogDir:   logDir,
		LogLevel: "INFO",
	}
}
