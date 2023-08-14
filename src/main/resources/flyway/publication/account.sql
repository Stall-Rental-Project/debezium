\c "emarketaccount"

drop publication if exists dbz_publication;

create publication dbz_publication
    for table
        role_permission;

alter table role_permission
    replica identity full;

