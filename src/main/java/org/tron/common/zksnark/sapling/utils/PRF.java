package org.tron.common.zksnark.sapling.utils;

import org.tron.common.zksnark.sapling.Librustzcash;

public class PRF {

  public static byte[] prfAsk(byte[] sk) {
    byte[] ask = null;
    byte t = 0x00;
    byte[] tmp = prfExpand(sk, t);
    Librustzcash.librustzcash_to_scalar(tmp, ask);
    return ask;
  }

  public static byte[] prfNsk(byte[] sk) {
    byte[] nsk = null;
    byte t = 0x01;
    byte[] tmp = prfExpand(sk, t);
    Librustzcash.librustzcash_to_scalar(tmp, nsk);
    return nsk;
  }

  public static byte[] prfOvk(byte[] sk) {
    byte[] ovk = new byte[32];
    byte t = 0x02;
    byte[] tmp = prfExpand(sk, t);
    System.arraycopy(tmp, 0, ovk, 0, 32);
    return ovk;
  }

  private static byte[] prfExpand(byte[] sk, byte t) {
    byte[] res = new byte[64];
    byte[] blob = new byte[33];

    System.arraycopy(sk, 0, blob, 0, 32);
    blob[32] = t;

    // todo:
    //    crypto_generichash_blake2b_state state;
    //    crypto_generichash_blake2b_init_salt_personal(
    //        state, nullptr, 0, 64, nullptr, ZCASH_EXPANDSEED_PERSONALIZATION);
    //    cryptoGenerichashBlake2BUpdate(state, blob, 33);
    //    cryptoGenerichashBlake2BFinal(state, res, 64);

    return res;
  }
}