package com.bll;

/**
 * Business Facade Interface.
 * Currently extends WordBO interface for simplicity.
 * In future, can extend multiple BO interfaces (RootBO, ExampleBO, etc.)
 */
public interface IBussinessFacade extends IWordBO {
    // Inherits all Word-related business operations
}
