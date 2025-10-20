package com.dal;

/**
 * Data Access Facade Interface.
 * Inherits all WordDAO operations (for now),
 * future DAOs (RootDAO, ExampleDAO, etc.) can be added here later.
 */
public interface IDataAccessFacade extends IWordDAO , IRootDAO{
    // For now, no new methods — inherits all from IWordDAO.
    // Later you can add RootDAO, ExampleDAO methods here.
}
