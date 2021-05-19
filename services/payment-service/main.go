package main

import (
	"github.com/jjoc007/poc-consumer-driven-contract-multi-language/payments/controller"
	"log"
	"net"
	"net/http"
)

func main() {
	mux := controller.GetHTTPHandler()

	ln, err := net.Listen("tcp", ":8087")
	if err != nil {
		log.Fatal(err)
	}
	defer ln.Close()

	log.Printf("API starting: port %d (%s)", 8087, ln.Addr())
	log.Printf("API terminating: %v", http.Serve(ln, mux))
}