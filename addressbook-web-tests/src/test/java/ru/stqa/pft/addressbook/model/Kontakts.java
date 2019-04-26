package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Kontakts extends ForwardingSet<KontaktData> {
   private Set<KontaktData> delegate;

   public Kontakts(Kontakts kontakts) {
      this.delegate = new HashSet<KontaktData>(kontakts.delegate);

   }

   public Kontakts() {
      this.delegate = new HashSet<KontaktData>();
   }

   @Override
   protected Set<KontaktData> delegate() {
      return delegate;
   }

   public Kontakts withadded (KontaktData kontakt){
      Kontakts kontakts = new Kontakts(this);
      kontakts.add(kontakt);
      return kontakts;

   }

   public Kontakts without (KontaktData kontakt){
      Kontakts kontakts = new Kontakts(this);
      kontakts.remove(kontakt);
      return kontakts;

   }

}
