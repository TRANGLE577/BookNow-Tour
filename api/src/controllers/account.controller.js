const accountService = require('../services/account.service');

// route functions

function getAll(req, res, next) {
    accountService.getAll()
        .then(data => res.json(data))
        .catch(next);
}

function getById(req, res, next) {
    accountService.getById(req.params.id)
        .then(data => res.json(data))
        .catch(next);
}

function deleteAccount(req, res, next) {
    console.log("delete");
    accountService.deleteAccount(req, res)
        .then(data => res.json(data))
        .catch(next);
};

module.exports = {
    getAll,
    getById,
    deleteAccount,
};

