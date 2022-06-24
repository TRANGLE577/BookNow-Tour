const locationService = require('../services/location.service');

// route functions

function getAll(req, res, next) {
  locationService.getAll()
      .then(data => res.json(data))
      .catch(next);
}

function getById(req, res, next) {
  locationService.getById(req.params.id)
      .then(data => res.json(data))
      .catch(next);
}

module.exports = {
  getAll,
  getById
};

