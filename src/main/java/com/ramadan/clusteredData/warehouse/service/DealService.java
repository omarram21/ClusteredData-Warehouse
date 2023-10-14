    package com.ramadan.clusteredData.warehouse.service;

    import com.ramadan.clusteredData.warehouse.Repository.DealRepository;
    import com.ramadan.clusteredData.warehouse.mapper.DealMapper;
    import com.ramadan.clusteredData.warehouse.model.DTO.DealDTO;
    import com.ramadan.clusteredData.warehouse.model.DealModel;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Service;
    import org.springframework.web.server.ResponseStatusException;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class DealService implements IDealService{
        private static final Logger logger = LoggerFactory.getLogger(DealService.class);
        private final DealRepository dealRepository;
        public DealService(DealRepository dealRepository) {
            this.dealRepository = dealRepository;
        }

        @Override
        public ResponseEntity<List<DealModel>> GetAll() {
            try {
                List<DealModel> deals = dealRepository.findAll();
                return ResponseEntity.ok(deals);
            } catch (Exception e) {
                logger.error("An error occurred while retrieving deals", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        @Override
        public ResponseEntity<DealModel> GetByID(Long id) {
            try {
                Optional<DealModel> deal = dealRepository.findById(id);
                if (deal.isPresent()) {
                    return ResponseEntity.ok(deal.get());
                } else {
                    logger.error("Deal with ID " + id + " not found");
                    return ResponseEntity.notFound().build();
                }
            } catch (Exception e) {
                logger.error("Something went wrong while saving the deal");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        @Override
        public ResponseEntity addNewDeal(DealDTO request) {
            var validationResult = request.isValid();

            if (!validationResult.getStatus()) {
                logger.error(validationResult.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request: " + validationResult.getMessage());
            }

            boolean isDuplicate = dealRepository.existsByFromCurrencyAndToCurrencyAndDealTimestampAndDealAmount(
                    request.getFromCurrency(),
                    request.getToCurrency(),
                    request.getDealTimestamp(),
                    request.getDealAmount()
            );

            if (isDuplicate) {
                logger.error("Duplicate deal found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate deal found");
            }

            try {
                DealModel savedDeal = dealRepository.save(DealMapper.mapDealDto(request));
                return ResponseEntity.status(HttpStatus.CREATED).body(savedDeal);
            } catch (Exception e) {
                logger.error("Something went wrong while saving the deal");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong while saving the deal");
            }
        }
    }
