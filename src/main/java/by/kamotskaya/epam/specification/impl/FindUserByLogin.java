package by.kamotskaya.epam.specification.impl;

import by.kamotskaya.epam.specification.BaseSpecification;

/**
 * @author Lena Kamotskaya
 */
public class FindUserByLogin implements BaseSpecification {

    private String login;

    @Override
    public boolean specified(String login) {
        this.login = login;
        return false;
    }
}
