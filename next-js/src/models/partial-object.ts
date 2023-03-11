export type PartialObject<T> = { _id: string};
export type NotYetCreated<T> = Omit<T, '_id'>;
export type SavedObject<T> = Partial<Record<'_id', string>> & T;
