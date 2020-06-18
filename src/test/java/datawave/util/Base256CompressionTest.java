package datawave.util;

import datawave.query.util.DateFrequencyValue;
import org.junit.Assert;
import org.junit.Test;

public class Base256CompressionTest {
    
    private boolean failedTest = false;
    
    @Test
    public void encodeThenDecodeTest() {
        
        for (int example = 1_950_000_000; example < 2_147_483_647; example++) {
            encodeThenDecode(example, false);
        }
        
        Assert.assertTrue(true);
        
    }
    
    private void encodeThenDecode(int example, boolean verbose) {
        byte[] result;
        result = DateFrequencyValue.Base256Compression.numToBytes(example);
        if (verbose) {
            System.out.println(example + " has " + result.length + " numbytes.");
            System.out.println("the length of result array is: " + result.length);
            System.out.println("the bytes are: ");
            
            for (byte abyte : result) {
                System.out.println(abyte & 0xFF);// abyte is being interpreted as the
                // binary two's complement representation.
            }
        }
        
        long inverseResult = DateFrequencyValue.Base256Compression.bytesToLong(result);
        
        if (inverseResult != example) {
            System.out.println("Decode did not decode example for " + example + " and decoded" + inverseResult);
            System.out.println("Test failed");
            failedTest = true;
            Assert.fail();
            if (verbose) {
                System.out.println("The example did not match in bytesToLong result");
                System.out.println("The example was " + example + " and the result was " + inverseResult);
            }
        } else {
            if (verbose)
                System.out.println("Byte array to long value is " + inverseResult);
        }
        
        if (verbose)
            System.out.println("\n");
    }
    
}