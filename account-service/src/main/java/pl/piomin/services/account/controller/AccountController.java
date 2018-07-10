package pl.piomin.services.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pl.piomin.services.account.model.Account;
import pl.piomin.services.account.repository.AccountRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountRepository repository;

	@RequestMapping(value = "/customer/{customer}", method = GET)
	public Flux<Account> findByCustomer(@PathVariable("customer") String customerId) {
		LOGGER.info("findByCustomer: customerId={}", customerId);
		return repository.findByCustomerId(customerId);
	}

	@RequestMapping(method = GET)
	public Flux<Account> findAll() {
		LOGGER.info("findAll");
		return repository.findAll();
	}

	@RequestMapping(value = "/{id}", method = GET)
	public Mono<Account> findById(@PathVariable("id") String id) {
		LOGGER.info("findById: id={}", id);
		return repository.findById(id);
	}

	@RequestMapping(method = POST)
	public Mono<Account> create(@RequestBody Account account) {
		LOGGER.info("create: {}", account);
		return repository.save(account);
	}
	
}
