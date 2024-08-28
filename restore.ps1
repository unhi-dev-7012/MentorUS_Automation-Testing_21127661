# PostgreSQL Database Credentials
$PGUSER = "postgres"
$PGPASSWORD = "12345"
$PGDATABASE = "mentor_us"
$BACKUP_FILE = "D:\MentorUS\dbBackup\backup_20240826_122300.dump"

# Run the pg_restore command to restore the backup
$env:PGPASSWORD = $PGPASSWORD
& "C:\Program Files\PostgreSQL\16\bin\pg_restore.exe" -U $PGUSER -d $PGDATABASE -v --clean --if-exists $BACKUP_FILE

Write-Output "Database restored from $BACKUP_FILE"