CREATE TABLE IF NOT EXISTS undo_log
(
    branch_id     BIGINT       NOT NULL,                      -- branch transaction id
    xid           VARCHAR(128) NOT NULL,                      -- global transaction id
    context       VARCHAR(128) NOT NULL,                      -- undo_log context, such as serialization
    rollback_info BYTEA        NOT NULL,                      -- rollback info (use BYTEA instead of LONGBLOB)
    log_status    INTEGER      NOT NULL,                      -- 0: normal status, 1: defense status
    log_created   TIMESTAMP(6) NOT NULL,                      -- create datetime
    log_modified  TIMESTAMP(6) NOT NULL,                      -- modify datetime
    CONSTRAINT ux_undo_log UNIQUE (xid, branch_id)           -- unique constraint on (xid, branch_id)
);

-- Adding index on log_created
CREATE INDEX ix_log_created ON undo_log(log_created);
