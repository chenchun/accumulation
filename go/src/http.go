package main

import (
	"bytes"
	"net/http"
	"log"
	"io/ioutil"
)

func main() {
	url := ""
	m := ``
	contentReader := bytes.NewReader([]byte(m))
	req, err := http.NewRequest("POST", url, contentReader)
	if err != nil {
		log.Fatalln(err)
	}
	req.Header.Set("Content-Type", "application/json")
	req.Header.Add("Accept", "applicaiton/json")
	client := &http.Client{}
	resp, err := client.Do(req)
	if err != nil {
		log.Fatalln(err)
	}
	body, err := ioutil.ReadAll(resp.Body)
	resp.Body.Read()
	log.Printf(string(body))
	defer resp.Body.Close()
}
