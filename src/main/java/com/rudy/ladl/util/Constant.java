package com.rudy.ladl.util;

public class Constant {
    public static final String REDIRECT = "redirect:";
    public static final String SLASH = "/";

    public static final String HOME_PATH = "/";
    public static final String HOME_PAGE = "homePage";

    public static final String REGISTRATION_PATH = "/register";
    public static final String REGISTRATION_PAGE = "registrationPage";

    public static final String LOGIN_PATH = "/login";
    public static final String LOGIN_PAGE = "loginPage";


    public static final String USERS_PATH = "/users";
    public static final String USER_PATH = "/user";

    public static final String USER_LIST_PAGE = "userListPage";
    public static final String USER_DETAILS_PAGE = "userDetailsPage";

    public static final String SITES_PATH = "/sites";

    public static final String SITE_LIST_PAGE = "siteListPage";
    public static final String SITE_DETAILS_PAGE = "siteDetailsPage";

    public static final String SITE_ADD_PATH = SITES_PATH + "/add";
    public static final String SITE_ADD_PAGE = "siteAddPage";

    public static final String SITE_ADD_OFFICIAL_TAG_PATH = SITES_PATH + "/addOfficialTag";
    public static final String SITE_REMOVE_OFFICIAL_TAG_PATH = SITES_PATH + "/removeOfficialTag";
    public static final String SITE_ADD_COMMENT_PATH = SITES_PATH + "/addComment";
    public static final String SITE_REMOVE_COMMENT_PATH = SITES_PATH + "/removeComment";
    public static final String SITE_SEARCH_PATH = SITES_PATH + "/search";

    public static final String SLASHID_PATH = "/{id}";
    public static final String SLASHSTRING_PATH = "/{string}";

    public static final String TOPOS_PATH = "/topos";
    public static final String TOPO_ADD_PATH = TOPOS_PATH + "/add";
    public static final String TOPO_ADD_PAGE = "topoAddPage";

    public static final String TOPO_LIST_PAGE = "topoListPage";
    public static final String USER_TOPO_LIST_PAGE = "myTopoListPage";
    public static final String USER_TOPO_LIST_PATH = TOPOS_PATH + "/myTopos";
    public static final String TOPO_DETAILS_PAGE = "topoDetailsPage";




    public static final String ERROR_MSG_PASSWORD_MISMATCH = "Les mots de passe ne sont pas identiques";
    public static final String ERROR_MSG_PASSWORD_NOT_COMPLETE = "Le mot de passe doit contenir au moins une majuscule";
    public static final String ERROR_MSG_USERNAME_INVALID_CHAR = "Le nom d'utilisateur ne peut pas contenir de caractère spéciaux";
    public static final String ERROR_MSG_USERNAME_NOT_AVAILABLE = "Ce pseudo n'est pas disponible";
    public static final String ERROR_MSG_EMAIL_NOT_AVAILABLE = "Cet email n'est pas disponible";
    public static final String ERROR_MSG_SITE_FIELD_ALREADY_FILLED = "Le champ de ce site à déjà une contribution";

    private Constant() {

    }

}
