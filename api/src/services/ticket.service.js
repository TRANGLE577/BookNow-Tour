const db = require("../models");

async function getAll() {
    return await db.Ticket.findAll({
        
    });
}

async function getById(id) {
    return await db.Ticket.findOne({
        where: {
            id: id,
        },
        
    });
}


module.exports = {
    getAll,
    getById,
};
