const multer = require('multer');

const tourService = require('../services/tour.service');

const storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, './uploads');
    },
    filename: function (req, file, cb) {
        cb(null, file.originalname);
    }
});

const uploadImg = multer({storage: storage}).single('image');

// route functions
function getAll(req, res, next) {
    tourService.getAll()
        .then(data => res.json(data))
        .catch(next);
}

function getByRandom(req, res, next) {
    tourService.getByRandom()
        .then(data => res.json(data))
        .catch(next);
}

function getBySearch(req, res, next) {
    tourService.getBySearch(req.query.keyword)
        .then(data => res.json(data))
        .catch(next);
}

function getById(req, res, next) {
    tourService.getById(req.params.id)
        .then(data => res.json(data))
        .catch(next);
}

function save(req, res, next) {
    console.log("save");
    tourService.save(req, res)
        .then(data => res.json(data))
        .catch(next);
};

function getReportTour(req, res, next) {
    tourService.getReportTour(req, res)
        .then(data => res.json(data))
        .catch(next);
}

function deleteTour(req, res, next) {
    console.log("delete");
    tourService.deleteTour(req, res)
        .then(data => res.json(data))
        .catch(next);
};

module.exports = {
    getAll,
    getByRandom,
    getBySearch,
    getById,
    save,
    uploadImg,
    getReportTour,
    deleteTour,
};

