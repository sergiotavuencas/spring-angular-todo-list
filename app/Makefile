MAVEN = mvn
CLEAN = clean
PACKAGE = package
RUN = spring-boot:run

.PHONY: clean
clean:
	$(MAVEN) $(CLEAN)

.PHONY: clean-package
clean-package:
	$(MAVEN) $(CLEAN) $(PACKAGE)

.PHONY: run
run:
	$(MAVEN) $(RUN)

.PHONY: clean-n-run
clean-n-run: clean run
