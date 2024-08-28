# PostgreSQL Database Credentials
$PGUSER = "postgres"
$PGPASSWORD = "12345"
$PGDATABASE = "mentor_us"
$BACKUP_DIR = "D:\MentorUS\dbBackup\"
$TIMESTAMP = Get-Date -Format "yyyyMMdd_HHmmss"
$BACKUP_FILE = "$BACKUP_DIR\backup_$TIMESTAMP.dump"

# Ensure the backup directory exists
if (-not (Test-Path $BACKUP_DIR)) {
    New-Item -Path $BACKUP_DIR -ItemType Directory
}

# Run the pg_dump command to create the backup
$env:PGPASSWORD = $PGPASSWORD
& "C:\Program Files\PostgreSQL\16\bin\pg_dump.exe" -U $PGUSER -F c -b -v -f $BACKUP_FILE $PGDATABASE

Write-Output "Backup created at $BACKUP_FILE"