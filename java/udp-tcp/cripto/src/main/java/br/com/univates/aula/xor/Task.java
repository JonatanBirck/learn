package br.com.univates.aula.xor;

import java.util.Objects;

public class Task 
{    
    private String key = null;
    private String messageEncrypt = null;
    private String messageDecrypt = null;
    private int[] messageBytes = null;
    private long checksum1 = 0;
    private String checksum2 = null;
    private long timeToResolved = 0;
    private boolean resolved = false;
    private int keyLength = 0;

    public static Task encrypt( String message, String key )
    {
        long checksum1 = 0;
        long checksum2 = 0;
        String messageEncrypt = "";

        int[] messageBytes = new int[ message.length() ];

        for (int i = 0; i < message.length(); i++) 
        {
            int p = i % key.length();
            messageBytes[i] = ( message.charAt(i) ^ key.charAt( p ) );
            messageEncrypt += (char) messageBytes[i];
            checksum1 += ( message.charAt(i) - 32 ) * ( i + 1 );
            checksum2 += checksum2 + ( checksum2 << 3 ) + ( message.charAt(i) - 32 );
        }

        Task task = new Task();

        task.checksum1( checksum1 )
            .checksum2( checksum2 + "" )
            .messageBytes( messageBytes )
            .messageEncrypt( messageEncrypt )
            .keyLength( key.length() );

        return task;
    }

    public static Task decrypt( String messageEncrypt, String key )
    {
        long checksum1 = 0;
        long checksum2 = 0;
        String messageDecrypt = "";

        int[] messageBytes = new int[ messageEncrypt.length() ];

        for (int i = 0; i < messageEncrypt.length(); i++) 
        {
            int p = i % key.length();
            messageBytes[i] = ( messageEncrypt.charAt(i) ^ key.charAt( p ) );
            messageDecrypt += (char) messageBytes[i];
            checksum1 += ( messageBytes[i] - 32 ) * ( i + 1 );
            checksum2 += checksum2 + ( checksum2 << 3 ) + ( messageBytes[i] - 32 );
        }

        Task task = new Task();

        task.checksum1( checksum1 )
            .checksum2( checksum2 + "" )
            .messageBytes( messageBytes )
            .messageDecrypt( messageDecrypt )
            .messageEncrypt( messageEncrypt )
            .keyLength( key.length() )
            .key( key )
            .resolved( true );

        return task;
    }

    public static Task resolve( Task task )
    {
        long startTimeToResolved = System.currentTimeMillis();

        while( true )
        {
            // generate Key
            String key = "";

            for( int i = 0; i < task.getKeyLength(); i++ ) 
            {
                key += (char) ( 32 + Math.random() * 95 ); //caracteres imprimíveis                
            }

            // test
            Task taskCandidate = Task.decrypt( task.getMessageEncrypt(), key );

            boolean equalChecksum1 = taskCandidate.getChecksum1() == task.getChecksum1();
            boolean equalChecksum2 = taskCandidate.getChecksum2().equals( task.getChecksum2() );

            if( equalChecksum1 && equalChecksum2 )
            {
                taskCandidate.setTimeToResolved( System.currentTimeMillis() - startTimeToResolved );
                return taskCandidate;
            }
        }
    }

    public Task() {
    }

    public Task(String key, String messageEncrypt, String messageDecrypt, int[] messageBytes, long checksum1, String checksum2, long timeToResolved, boolean resolved, int keyLength) {
        this.key = key;
        this.messageEncrypt = messageEncrypt;
        this.messageDecrypt = messageDecrypt;
        this.messageBytes = messageBytes;
        this.checksum1 = checksum1;
        this.checksum2 = checksum2;
        this.timeToResolved = timeToResolved;
        this.resolved = resolved;
        this.keyLength = keyLength;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessageEncrypt() {
        return this.messageEncrypt;
    }

    public void setMessageEncrypt(String messageEncrypt) {
        this.messageEncrypt = messageEncrypt;
    }

    public String getMessageDecrypt() {
        return this.messageDecrypt;
    }

    public void setMessageDecrypt(String messageDecrypt) {
        this.messageDecrypt = messageDecrypt;
    }

    public int[] getMessageBytes() {
        return this.messageBytes;
    }

    public void setMessageBytes(int[] messageBytes) {
        this.messageBytes = messageBytes;
    }

    public long getChecksum1() {
        return this.checksum1;
    }

    public void setChecksum1(long checksum1) {
        this.checksum1 = checksum1;
    }

    public String getChecksum2() {
        return this.checksum2;
    }

    public void setChecksum2(String checksum2) {
        this.checksum2 = checksum2;
    }

    public long getTimeToResolved() {
        return this.timeToResolved;
    }

    public void setTimeToResolved(long timeToResolved) {
        this.timeToResolved = timeToResolved;
    }

    public boolean isResolved() {
        return this.resolved;
    }

    public boolean getResolved() {
        return this.resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public int getKeyLength() {
        return this.keyLength;
    }

    public void setKeyLength(int keyLength) {
        this.keyLength = keyLength;
    }

    public Task key(String key) {
        setKey(key);
        return this;
    }

    public Task messageEncrypt(String messageEncrypt) {
        setMessageEncrypt(messageEncrypt);
        return this;
    }

    public Task messageDecrypt(String messageDecrypt) {
        setMessageDecrypt(messageDecrypt);
        return this;
    }

    public Task messageBytes(int[] messageBytes) {
        setMessageBytes(messageBytes);
        return this;
    }

    public Task checksum1(long checksum1) {
        setChecksum1(checksum1);
        return this;
    }

    public Task checksum2(String checksum2) {
        setChecksum2(checksum2);
        return this;
    }

    public Task timeToResolved(long timeToResolved) {
        setTimeToResolved(timeToResolved);
        return this;
    }

    public Task resolved(boolean resolved) {
        setResolved(resolved);
        return this;
    }

    public Task keyLength(int keyLength) {
        setKeyLength(keyLength);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(key, task.key) && Objects.equals(messageEncrypt, task.messageEncrypt) && Objects.equals(messageDecrypt, task.messageDecrypt) && Objects.equals(messageBytes, task.messageBytes) && checksum1 == task.checksum1 && Objects.equals(checksum2, task.checksum2) && timeToResolved == task.timeToResolved && resolved == task.resolved && keyLength == task.keyLength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, messageEncrypt, messageDecrypt, messageBytes, checksum1, checksum2, timeToResolved, resolved, keyLength);
    }

    @Override
    public String toString() {
        return "{" +
            " key='" + getKey() + "'" +
            ", messageEncrypt='" + getMessageEncrypt() + "'" +
            ", messageDecrypt='" + getMessageDecrypt() + "'" +
            ", messageBytes='" + getMessageBytes() + "'" +
            ", checksum1='" + getChecksum1() + "'" +
            ", checksum2='" + getChecksum2() + "'" +
            ", timeToResolved='" + getTimeToResolved() + "'" +
            ", resolved='" + isResolved() + "'" +
            ", keyLength='" + getKeyLength() + "'" +
            "}";
    }
}
